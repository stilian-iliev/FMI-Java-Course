package com.fmi.generics.services.impl;

import com.fmi.generics.exceptions.CapacityExceededException;
import com.fmi.generics.exceptions.ParcelNotFoundException;
import com.fmi.generics.models.MJTExpressLabel;
import com.fmi.generics.repositories.WarehouseRepository;
import com.fmi.generics.services.DeliveryServiceWarehouse;

import java.time.LocalDateTime;
import java.util.Map;

public class MJTExpressWarehouse<L, P> implements DeliveryServiceWarehouse<L, P> {
	private final WarehouseRepository<L, P> warehouseRepository;
	private final int capacity;
	private int retentionPeriod;

	public MJTExpressWarehouse(int capacity, int retentionPeriod) {
		this.warehouseRepository = new WarehouseRepository<>();
		this.capacity = capacity;
		this.retentionPeriod = retentionPeriod;
	}

	@Override
	public void submitParcel(L label, P parcel, LocalDateTime submissionDate) throws CapacityExceededException {
		validateSubmitParcelInput(label, parcel, submissionDate);
		
		if (!hasSpaceForNewItem()) {
			MJTExpressLabel<L> oldestLable = warehouseRepository.getOldestLable();
			
			if (canBeEvicted(oldestLable)) {
				warehouseRepository.remove(oldestLable.getLabel());
			} else {
				throw new CapacityExceededException();
			}
		}
		
		warehouseRepository.add(label, parcel, submissionDate);
	}

	private boolean canBeEvicted(MJTExpressLabel<L> lable) {
		LocalDateTime lableExpirationDate = lable.getSubmissionDate().plusDays(retentionPeriod);
		boolean lableIsExpired = lableExpirationDate.isBefore(LocalDateTime.now());
		
		return lableIsExpired;
	}

	private boolean hasSpaceForNewItem() {
		return (warehouseRepository.count() < capacity);
	}

	private void validateSubmitParcelInput(L label, P parcel, LocalDateTime submissionDate) {
		ensureNotNull(label, null);
		ensureNotNull(parcel, null);
		ensureNotNull(submissionDate, null);
		ensureDateNotInFuture(submissionDate);

	}

	private void ensureDateNotInFuture(LocalDateTime submissionDate) {
		boolean inFuture = submissionDate.isAfter(LocalDateTime.now());
		if (inFuture) {
			throw new IllegalArgumentException();
		}
		
	}

	private void ensureNotNull(Object object, String errorMsg) {
		if (object == null) {
			throw new IllegalArgumentException(errorMsg);
		}
	}

	@Override
	public P getParcel(L label) {
		ensureNotNull(label, null);
		P parcel = warehouseRepository.findByLabel(label);
		
		return parcel;
	}

	@Override
	public P deliverParcel(L label) throws ParcelNotFoundException {
		ensureNotNull(label, null);
		P toDelete = warehouseRepository.findByLabel(label);
		
		if (toDelete == null) {
			throw new ParcelNotFoundException();
		}
		
		warehouseRepository.remove(label);
		return toDelete;
	}

	@Override
	public double getWarehouseSpaceLeft() {
		long leftSpace = capacity - warehouseRepository.count();
		return leftSpace;
	}

	@Override
	public Map<L, P> getWarehouseItems() {
		Map<L, P> warehouseItems = warehouseRepository.findAll();
		return warehouseItems;
	}

	@Override
	public Map<L, P> deliverParcelsSubmittedBefore(LocalDateTime before) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<L, P> deliverParcelsSubmittedAfter(LocalDateTime after) {
		// TODO Auto-generated method stub
		return null;
	}

}
