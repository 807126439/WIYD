package com.wb.core.common.dao.utils;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class ConditionQuery {

	private List<Criterion> criterions = new ArrayList<Criterion>();

	public void add(Criterion criterion) {
		criterions.add(criterion);
	}

	// 与条件
	public void build(Criteria criteria) {
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
	}

	// 或条件
	public void buildOr(Criteria criteria) {
		switch (criterions.size()) {
		case 1:
			criteria.add((Criterion) Restrictions.or(criterions.get(0), criterions.get(0)));
			break;
		case 2:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),
					criterions.get(1)));
			break;
		case 3:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1), criterions.get(2))
					));
			break;
		case 4:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),criterions.get(3)))
					 ));
			break;
		case 5:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),Restrictions.or(criterions.get(3),criterions.get(4))))
					));
			break;
		case 6:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),Restrictions.or(criterions.get(3),
					Restrictions.or(criterions.get(4),criterions.get(5)))))
					));
			break;
		case 7:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),Restrictions.or(criterions.get(3),
					Restrictions.or(criterions.get(4),Restrictions.or(criterions.get(5),criterions.get(6))))))
					));
			break;
		case 8:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),Restrictions.or(criterions.get(3),
					Restrictions.or(criterions.get(4),Restrictions.or(criterions.get(5),Restrictions.or(criterions.get(6),criterions.get(7)))))))
					));
			break;
		case 9:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),Restrictions.or(criterions.get(3),
					Restrictions.or(criterions.get(4),Restrictions.or(criterions.get(5),Restrictions.or(criterions.get(6),Restrictions.or(criterions.get(7),criterions.get(8))))))))
					));
			break;
		case 10:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),Restrictions.or(criterions.get(3),
					Restrictions.or(criterions.get(4),Restrictions.or(criterions.get(5),Restrictions.or(criterions.get(6),Restrictions.or(criterions.get(7),Restrictions.or(criterions.get(8),criterions.get(9)))))))))
					));
			break;
		case 11:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),Restrictions.or(criterions.get(3),
					Restrictions.or(criterions.get(4),Restrictions.or(criterions.get(5),Restrictions.or(criterions.get(6),Restrictions.or(criterions.get(7),Restrictions.or(criterions.get(8),Restrictions.or(criterions.get(9),criterions.get(10))))))))))
					));
			break;
		case 12:
			criteria.add((Criterion) Restrictions.or(criterions.get(0),Restrictions.or(criterions.get(1),Restrictions.or(criterions.get(2),Restrictions.or(criterions.get(3),
					Restrictions.or(criterions.get(4),Restrictions.or(criterions.get(5),Restrictions.or(criterions.get(6),Restrictions.or(criterions.get(7),Restrictions.or(criterions.get(8),Restrictions.or(criterions.get(9),Restrictions.or(criterions.get(10),criterions.get(11)))))))))))
					));
			break;
			
		}

	}
}
