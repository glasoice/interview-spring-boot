package com.echevarne.sap.cloud.interview.services.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.echevarne.sap.cloud.interview.model.BasicEntity;

/**
 * Crudservice Impl base development of the CRUD application
 * 
 *
 */
public abstract class CrudServiceImpl<ModelObjectType extends BasicEntity, KeyType extends Serializable> {

	private JpaRepository<ModelObjectType, KeyType> repository;
	@SuppressWarnings("rawtypes")
	private Class domainClass;
	protected final transient Logger logger = LoggerFactory.getLogger(getDomainClass());

	public ModelObjectType create(ModelObjectType entity) {
		entity.setEntityCreationTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
		return saveEntity(entity);
	}
	
  /**
   * This method is useful when we need to save entity but we need to clear cache as well.
   * 
   * @param entity to persist
   * @return 
   */
	public ModelObjectType createAndFlush(ModelObjectType entity) {
		return repository.saveAndFlush(entity);
	}

  /**
   * 
   * @param entity to update based on generics
   */
	public void update(ModelObjectType entity) {
		saveEntity(entity);
	}
	
  /**
   * This method is useful when we need to update entity but we need to clear cache as well.
   * 
   * @param entity to update
   */
	public ModelObjectType updateAndFlush(ModelObjectType entity) {
		return repository.saveAndFlush(entity);
	}

  /**
   * Save entity
   * 
   * @param entity
   * @return
   */
	private ModelObjectType saveEntity(ModelObjectType entity) {
		return repository.save(entity);
	}

	public void remove(ModelObjectType entity) {
		repository.delete(entity);
	}
	
    /**
   * This method is useful when we need to remove entity but we need to clear cache as well.
   * 
   * @param entity 
   */
	public void removeAndFlush(ModelObjectType entity) {
		repository.delete(entity);
		repository.flush();
	}

	public void setCrudRepository(JpaRepository<ModelObjectType, KeyType> repository) {
		this.repository = repository;
	}

	public JpaRepository<ModelObjectType, KeyType> getCrudRepository() {
		return this.repository;
	}

  /**
   * Based on Spring Data we are getting this information
   * 
   * @param entityId to get based on generics
   * @return 
   */
	public Optional<ModelObjectType> findById(KeyType entityId) {

		Class<?> type = getDomainClass();
		try {
			return repository.findById(entityId);
		} catch (ObjectRetrievalFailureException e) {
			if (logger.isWarnEnabled()) {
				logger.warn(String.format("object instance of %s identified by %s not found in the database",
						type.getName(), entityId));
			}
			return Optional.empty();
		}
	}
	
	/**
   * Based on Spring Data we are getting this information
   * 
   * @param entityIds to get based on generics
   * @return 
   */
	public List<ModelObjectType> findAllById(List<KeyType> entityIds) {
		
		return (List<ModelObjectType>) repository.findAllById(entityIds);
		
	}

	@SuppressWarnings("unchecked")
	public List<ModelObjectType> getAll(@SuppressWarnings("rawtypes") CrudRepository crudRepository) {
		return (List<ModelObjectType>) crudRepository.findAll();
	}
	
	public List<ModelObjectType> getAll() {
		return getAll( getCrudRepository() );
	}

	protected Class<?> getDomainClass() {
		if (domainClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			domainClass = (Class<?>) thisType.getActualTypeArguments()[0];
		}
		return domainClass;
	}
	
}
