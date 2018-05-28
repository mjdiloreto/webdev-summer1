package com.example.webdevsummer12018.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String widgetName;
	
	private Integer widgetOrder;
	
	private String widgetText;
	
	private String className;
	
	private String style;
	
	private String width;
	
	private String height;
	
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	/********************************
	 * LIST WIDGET ATTRIBUTES       *
	 ********************************/
	
	// newline delimitted
	private String listItems;

	private String listType;
	
	/********************************
	 * PARAGRAPH WIDGET ATTRIBUTES  *
	 ********************************/
	
	/********************************
	 * IMAGE WIDGET ATTRIBUTES      *
	 ********************************/
	
	private String src;
	
	/********************************
	 * LINK WIDGET ATTRIBUTES       *
	 ********************************/
	
	private String href;
	
	/********************************
	 * HEADING WIDGET ATTRIBUTES    *
	 ********************************/
	
	// Corresponding to h1, h2, etc.
	private Integer size;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return widgetName;
	}

	public void setName(String name) {
		this.widgetName = name;
	}

	public Integer getOrder() {
		return widgetOrder;
	}

	public void setOrder(int order) {
		this.widgetOrder = order;
	}

	public String getText() {
		return widgetText;
	}

	public void setText(String text) {
		this.widgetText = text;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public String getListItems() {
		return listItems;
	}

	public void setListItems(String listItems) {
		this.listItems = listItems;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	public void setAll(Widget w) {
		if(w.getName() != null) {
			this.setName(w.getName());
		}
		if(w.getOrder() != null) {
			this.setOrder(w.getOrder());
		}
		if(w.getText() != null) {
			this.setText(w.getText());
		}
		if(w.getClassName() != null) {
			this.setClassName(w.getClassName());
		}
		if(w.getStyle() != null) {
			this.setStyle(w.getStyle());
		}
		if(w.getWidth() != null) {
			this.setWidth(w.getWidth());
		}
		if(w.getHeight() != null) {
			this.setHeight(w.getHeight());
		}
		if(w.getLesson() != null) {
			this.setLesson(w.getLesson());
		}
		if(w.getListItems() != null) {
			this.setListItems(w.getListItems());
		}
		if(w.getListType() != null) {
			this.setListType(w.getListType());
		}
		if(w.getSrc() != null) {
			this.setSrc(w.getSrc());
		}
		if(w.getHref() != null) {
			this.setHref(w.getHref());
		}
		if(w.getSize() != null) {
			this.setSize(w.getSize());
		}
	}
	
}
