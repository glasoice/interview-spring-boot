package com.echevarne.sap.cloud.interview.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("rawtypes")
@MappedSuperclass
public abstract class OrdenesVentas implements Serializable, Cloneable, Comparable {
	
	/**
	 * 
	 *

	/**
	 * Id used as primary key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	private Date fechaPedido;
	private int cantidadRequerida;
	private String unidadBase;
	
	/**
	 * Timestamp that marks the moment when the entity got created.
	 */
	@Column(nullable = false, updatable = false)
	protected Timestamp entityCreationTimestamp;

	/**
	 * Timestamp that marks the moment of the last update made to the entity.
	 */
	@Column(nullable = false)
	protected Timestamp lastUpdatedTimestamp;
	
	/**
	 * Current version (incremental value) of the entity, used for optimistic.
	 * locking
	 */
	@Column(nullable = false)
	@Version
	protected long entityVersion;
	
	/**
	 * Sets creation time
	 */
	@PrePersist
	public void prePersist() {
	    entityCreationTimestamp = new Timestamp(System.currentTimeMillis());
	    lastUpdatedTimestamp = entityCreationTimestamp;
	}
	
	/**
	 * Updates update time
	 */
	@PreUpdate
	public void preUpdate() {
		lastUpdatedTimestamp = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getEntityCreationTimestamp() {
		return entityCreationTimestamp;
	}

	public void setEntityCreationTimestamp(Timestamp entityCreationTimestamp) {
		this.entityCreationTimestamp = entityCreationTimestamp;
	}

	public long getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(long entityVersion) {
		this.entityVersion = entityVersion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public int getCantidadRequerida() {
		return cantidadRequerida;
	}

	public void setCantidadRequerida(int cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}

	public String getUnidadBase() {
		return unidadBase;
	}

	public void setUnidadBase(String unidadBase) {
		this.unidadBase = unidadBase;
	}

	public Timestamp getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	
	/**
	 * Basic wrapper for the {@link OrdenesVentas#onEquals(Object)} method that must
	 * be implemented by all <tt>BasicEntity</tt> subclasses.
	 *
	 * @param o the reference object with which to compare.
	 *
	 * @return <tt>true</tt> if this object is considered to be equal to the
	 *         argument; <tt>false</tt> otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof OrdenesVentas) {
			OrdenesVentas e = (OrdenesVentas) o;
			Long thisId = getId();
			Long otherId = e.getId();
			if (thisId != null && otherId != null) {
				return thisId.equals(otherId) && getClass().equals(e.getClass());
			} else {
				return onEquals(e);
			}
		}
		return false;
	}

	/**
	 * Equality testing method for all persistent classes.
	 * <p/>
	 * This method's implementation must be designed and coded carefully. Since we
	 * are using Hibernate 3 as a persistence framework and entity identifiers are
	 * being generated by the DBMS, we cannot rely on Hibernate object identifiers
	 * (i.e., the <tt>BasicEntity.id</tt> field) for the <tt>equals()</tt> and
	 * <tt>hashCode()</tt> methods since it may not always have been set before
	 * these methods are called. Instead, the <tt>equals()</tt> and
	 * <tt>hashCode()</tt> methods must be based on a business key. The business key
	 * property value(s) must not change during the scope of a Hibernate session or
	 * while the persistent object belongs to a java.util.Collection (such as
	 * java.util.HashMap or java.util.HashSet).
	 * <p/>
	 * Additionally, the <tt>onEquals()</tt> and <tt>onHashCode()</tt>
	 * implementations must respect the <tt>equals()</tt> and <tt>hashCode()</tt>
	 * contract; please refer to the corresponding documentation for
	 * {@link Object#equals(Object)} and {@link Object#hashCode()}.
	 *
	 * @param o the reference object with which to compare.
	 *
	 * @return <tt>true</tt> if this object is considered to be equal to the
	 *         argument; <tt>false</tt> otherwise.
	 *
	 * @see OrdenesVentas#equals(Object)
	 * @see Object#equals(Object)
	 * @see Object#hashCode()
	 */
	public abstract boolean onEquals(Object o);

	/**
	 * Basic wrapper for the {@link OrdenesVentas#onHashCode(int)} method that must be
	 * implemented by all <tt>BasicEntity</tt> subclasses.
	 *
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 6;
		return onHashCode(result);
	}

	/**
	 * Hashcode method for all persistent classes.
	 * <p/>
	 * The properties that a subclass may use for calculating a hashcode must be the
	 * same properties that are used to implement the
	 * {@link OrdenesVentas#onEquals(Object)} method.
	 *
	 * @param result the result of the hashCode function from the parent class.
	 *
	 * @return a hash code value for this object.
	 *
	 * @see OrdenesVentas#onEquals(Object)
	 * @see Object#hashCode()
	 */
	public abstract int onHashCode(int result);

	@Override
	protected Object clone() throws CloneNotSupportedException {
		try {
			OrdenesVentas cloneEntity = (OrdenesVentas) super.clone();
			cloneEntity.setId(null);
			cloneEntity.setEntityCreationTimestamp(null);
			cloneEntity.setEntityVersion(-1);
			cloneEntity.setLastUpdatedTimestamp(null);
			return cloneEntity;
		} catch (CloneNotSupportedException e) {
			// shouldn't ever happen since this class is Cloneable and
			// a direct subclass of Object
			throw new InternalError("Unable to clone object of type [" + getClass().getName() + "]");
		}
	}

	/**
	 * Returns a StringBuilder representing the toString function of the class
	 * implementation. This should be overridden by all children classes using
	 * <tt>super.toStringBuilder()</tt> as the base, adding properties to the
	 * returned StringBuilder.
	 *
	 * @return a <tt>StringBuilder</tt> representing the <tt>toString</tt> value of
	 *         this object.
	 */
	protected StringBuilder toStringBuilder() {
		StringBuilder sb = new StringBuilder();
		sb.append("class name = ").append(getClass().getName()).append(", id = ").append(id);
		return sb;
	}

	/**
	 * Returns toStringBuilder().toString(). Declared as 'final' to require
	 * subclasses to override the {@link #toStringBuilder()} method, a cleaner and
	 * better performing mechanism for toString();
	 *
	 * @return toStringBuilder().toString()
	 */
	@Override
	public String toString() {
		return toStringBuilder().insert(0, '[').append(']').toString();
	}

	@Override
	public int compareTo(Object o) {
		return this.getEntityCreationTimestamp().compareTo(((OrdenesVentas) o).getEntityCreationTimestamp());
	}
}