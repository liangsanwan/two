package com.example.dbutils.entity;

import com.lidroid.xutils.db.annotation.Table;
@Table(name="sss")
public class UserEntity {
	private int id;
	private String name;
	private String content;
    private int age;
    private int hight;

 
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) { 
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", content="
				+ content + ", age=" + age + ", hight=" + hight + "]";
	}

	

}
