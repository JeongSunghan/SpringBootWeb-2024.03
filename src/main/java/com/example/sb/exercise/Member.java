package com.example.sb.exercise;

public class Member {
	private int mid;
	private String name;
	private int age;

	public Member() { }
	public Member(int mid, String name, int age) {
		this.mid = mid;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Member [mid=" + mid + ", name=" + name + ", age=" + age + "]";
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
