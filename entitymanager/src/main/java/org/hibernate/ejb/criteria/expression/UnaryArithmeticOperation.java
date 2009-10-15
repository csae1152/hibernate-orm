/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2009 by Red Hat Inc and/or its affiliates or by
 * third-party contributors as indicated by either @author tags or express
 * copyright attribution statements applied by the authors.  All
 * third-party contributions are distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.ejb.criteria.expression;

import javax.persistence.criteria.Expression;
import org.hibernate.ejb.criteria.ParameterRegistry;
import org.hibernate.ejb.criteria.QueryBuilderImpl;

/**
 * Models unary arithmetic operation (unary plus and unary minus).
 *
 * @author Steve Ebersole
 */
public class UnaryArithmeticOperation<T> 
		extends ExpressionImpl<T>
		implements UnaryOperatorExpression<T> {

	public static enum Operation {
		UNARY_PLUS, UNARY_MINUS
	}

	private final Operation operation;
	private final Expression<T> operand;

	public UnaryArithmeticOperation(
			QueryBuilderImpl queryBuilder,
			Operation operation,
			Expression<T> operand) {
		super( queryBuilder, (Class)operand.getJavaType() );
		this.operation = operation;
		this.operand = operand;
	}

	public Operation getOperation() {
		return operation;
	}

	/**
	 * {@inheritDoc}
	 */
	public Expression<T> getOperand() {
		return operand;
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerParameters(ParameterRegistry registry) {
		Helper.possibleParameter( getOperand(), registry );
	}

}
