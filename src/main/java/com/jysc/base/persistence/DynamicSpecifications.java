package com.jysc.base.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification; 
import com.google.common.collect.Lists;

public class DynamicSpecifications {

	public static <T> Specification<T> bySearchFilter(
			final Collection<SearchFilter> filters, final Class<T> entityClazz) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
				if (filters != null && filters.size() > 0) {

					List<Predicate> predicates = Lists.newArrayList();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName,
						// 转换为Task.user.name属性
					    System.out.println(filter.fieldName+" file filedname");
					    System.out.println(filter.value+" file value");
						String[] names = StringUtils.split(filter.fieldName,
								".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}
						Object value = filter.value;
						if (expression.getJavaType() == Date.class) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd");
							try {
								value = sdf.parse((String) filter.value);
							} catch (ParseException e) {
								continue;
							}
						}

						// logic operator
						switch (filter.operator) {
						case EQ:
							predicates.add(builder.equal(expression,
									filter.value));
							break;
						case NEQ:
							predicates.add(builder.notEqual(expression,
									filter.value));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%"
									+ filter.value + "%"));
							break;
						case NLIKE:
							predicates.add(builder.notLike(expression, "%"
									+ filter.value + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression,
									(Comparable) value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression,
									(Comparable) value));
							break;
						case GE:
							predicates.add(builder.greaterThanOrEqualTo(
									expression, (Comparable) value));
							break;
						case LE:
							predicates.add(builder.lessThanOrEqualTo(
									expression, (Comparable) value));
							break;
						case TF:
							if ("TRUE".equals(filter.value)) {
								predicates.add(builder.isTrue(expression));
								break;
							} else if ("FALSE".equals(filter.value)) {
								predicates.add(builder.isFalse(expression));
								break;
							}
						case IN:
							Iterator it = ((Iterable) value).iterator();
							CriteriaBuilder.In in = builder.in(expression);
							while (it.hasNext()) {
								Object v = it.next();
								in.value(v);
							}
							predicates.add(in);

						}

					}

					// 将所有条件用 and 联合起来
					if (predicates.size() > 0) {
						return builder.and(predicates
								.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}
	
	
	
	public static String bySearchFilter(final Collection<SearchFilter> filters) {
	                StringBuilder sbu = new StringBuilder();
	                
                if (filters != null && filters.size() > 0) {        
                   
                    for (SearchFilter filter : filters) {
                        // nested path translate, 如Task的名为"user.name"的filedName,
                        // 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.fieldName,
                                ".");
                        sbu.append(" and tab_name.");
                        // logic operator
                        switch (filter.operator) {
                        case EQ:
                            sbu.append(filter.fieldName);
                            sbu.append(" ='");
                            sbu.append(filter.value);
                            sbu.append("'  ");
                            break;
                        case NEQ:
                            sbu.append(filter.fieldName);
                            sbu.append(" !='");
                            sbu.append(filter.value);
                            sbu.append("'  ");
                            break;
                        case LIKE:
                            sbu.append(filter.fieldName);
                            sbu.append(" like '%");
                            sbu.append(filter.value);
                            sbu.append("%' ");
                            break;
                        case NLIKE:
                            sbu.append(filter.fieldName);
                            sbu.append(" not like '%");
                            sbu.append(filter.value);
                            sbu.append("%' ");
                            break;
                        case GT:
                            sbu.append(filter.fieldName);
                            sbu.append(" > '");
                            sbu.append(filter.value);
                            sbu.append("' ");
                            break;
                        case LT:
                            sbu.append(filter.fieldName);
                            sbu.append(" < '");
                            sbu.append(filter.value);
                            sbu.append("' ");
                            break;
                        case GE:
                            sbu.append(filter.fieldName);
                            sbu.append(" >= '");
                            sbu.append(filter.value);
                            sbu.append("' ");
                            break;
                        case LE:
                            sbu.append(filter.fieldName);
                            sbu.append(" <= '");
                            sbu.append(filter.value);
                            sbu.append("' ");
                            break;
              
                        case IN:
                            Iterator it = ((Iterable) filter.value).iterator();
                            sbu.append(filter.fieldName);
                            sbu.append(" in (");
                            while (it.hasNext()) {
                                Object v = it.next();
                                sbu.append(filter.value);
                                sbu.append(",");  
                            }
                            sbu.setLength(sbu.length() - 1);//去掉最后一个逗号
                            sbu.append(" )");
                        }

                    }
                    return sbu.toString();
                }

                return sbu.toString();
            }
        
    
}
