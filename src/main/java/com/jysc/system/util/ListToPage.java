package com.jysc.system.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * 模拟代替Service.query
 * 自定义List转Page返回到js
 */
public class ListToPage {
	
	public static <T> Page<T> trans (List<T> list, int pageNumber, int pageSize){
		final int totalElements = list.size();
		final int totalPages = (totalElements-1)/pageSize + 1;
		final int size = pageSize;
		final int number = pageNumber - 1;
		final int numberOfElements = (number!=totalPages-1)?size:(totalElements-number*size);
		final boolean first = (number == 0);
		final boolean last = (number == totalPages-1);
		System.out.println("哦耶 条数" + totalElements+"总共页数 "+totalPages+" 每页条数"+size+"当前页 "+number+"剩余条数 "+numberOfElements+" "+first+" "+last);
		if(number>totalPages-1){
			return null;	
		} 
		final List<T> sublistPage = list.subList(number*size, number*size+numberOfElements);
				
		
		Page<T> results = new Page<T>() {
			@Override public Iterator<T> iterator() { return null; }
			@Override public Pageable previousPageable() { return null; }
			@Override public Pageable nextPageable() { return null; }
			@Override public boolean isLast() { return last; }			//last
			@Override public boolean isFirst() { return first; }		//first
			@Override public boolean hasPrevious() { return false; }
			@Override public boolean hasNext() { return false; }
			@Override public boolean hasContent() { return true; }
			@Override public Sort getSort() { return null; }
			@Override public int getSize() { return size; }				//size
			@Override public int getNumberOfElements() { return numberOfElements; }	//numberOfElements
			@Override public int getNumber() { return number; }			//number
			@Override public List<T> getContent() { return sublistPage; }	//goodCartPage

			@Override public int getTotalPages() { return totalPages; }	//totalPages
			@Override public long getTotalElements() { return totalElements; }	//totalElements
			@Override
			public <U> Page<U> map(Function<? super T, ? extends U> converter) {
				// TODO Auto-generated method stub
				return null;
			}
		}; 
		return results;
	}
}