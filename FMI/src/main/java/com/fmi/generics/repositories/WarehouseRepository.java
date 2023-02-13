package com.fmi.generics.repositories;

import com.fmi.generics.models.MJTExpressLabel;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class WarehouseRepository<L,P> {
	private Map<L,P> items;
	private SortedSet<MJTExpressLabel<L>> submissionDates;
	
	public WarehouseRepository(){
		items = new HashMap<>();
		submissionDates = new TreeSet<>();
	}
	
	public long count() {
		return items.size();
	}

	public void add(L label, P parcel, LocalDateTime submissionDate) {
		items.put(label, parcel);
		submissionDates.add(new MJTExpressLabel<L>(label, submissionDate));
	}

	public MJTExpressLabel<L> getOldestLable() {
		return submissionDates.first();
	}
	
	public void remove(L label) {
		items.remove(label);
		submissionDates.remove(new MJTExpressLabel<L>(label));
	}

	public P findByLabel(L label) {
		return items.get(label);
	}

	public Map<L, P> findAll() {
		return items;
	}

}
