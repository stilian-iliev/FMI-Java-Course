package com.fmi.generics.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class MJTExpressLabel<L> implements Comparable<MJTExpressLabel<L>> {
	private L label;
	private LocalDateTime submissionDate;

	public MJTExpressLabel(L lable, LocalDateTime submissionDate) {
		this.label = lable;
		this.submissionDate = submissionDate;
	}

	public MJTExpressLabel(L lable) {
		this.label = lable;
	}

	public L getLabel() {
		return label;
	}

	public LocalDateTime getSubmissionDate() {
		return submissionDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		MJTExpressLabel<?> other = (MJTExpressLabel<?>) obj;
		return Objects.equals(label, other.label);
	}

	@Override
	public int compareTo(MJTExpressLabel<L> o) {
		if (this.submissionDate != null && o.submissionDate != null) {
            int datesCompare = this.submissionDate.compareTo(o.submissionDate);
            if (datesCompare != 0) {
                return datesCompare;
            }
        }
        if (this.label != null && o.label != null && this.label.equals(o.label)) {
            return 0;
        }

        return 1;
	}

}
