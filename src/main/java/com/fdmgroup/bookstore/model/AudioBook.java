package com.fdmgroup.bookstore.model;

import java.util.Objects;

public class AudioBook extends Book {

	private int timeLengthSeconds;

	public AudioBook(int itemId, double price, String title, String author, BookGenre bookGenre,
			int timeLengthSeconds) {
		super(itemId, price, title, author, bookGenre);
		this.timeLengthSeconds = timeLengthSeconds;
	}

	public int getTimeLengthSeconds() {
		return timeLengthSeconds;
	}

	public void setTimeLengthSeconds(int timeLengthSeconds) {
		this.timeLengthSeconds = timeLengthSeconds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(timeLengthSeconds);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AudioBook other = (AudioBook) obj;
		return timeLengthSeconds == other.timeLengthSeconds;
	}

	@Override
	public String toString() {
		return "AudioBook [timeLengthSeconds=" + timeLengthSeconds + "]";
	}

}
