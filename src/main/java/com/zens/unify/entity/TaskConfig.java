package com.zens.unify.entity;

public class TaskConfig {

	public TaskConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskConfig(String code) {
		super();
		this.code = code;
	}

	private Long id;

    private String name;

    private String url;

    private String code;

    private String cycle;

    private Integer status;

    private String menuUrl;

    private Integer isTask;
    
    private String expand;

	public Long getId() {
		return id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCycle() {
		return cycle;
	}


	public void setCycle(String cycle) {
		this.cycle = cycle;
	}


	public String getMenuUrl() {
		return menuUrl;
	}


	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getIsTask() {
		return isTask;
	}

	public void setIsTask(Integer isTask) {
		this.isTask = isTask;
	}

	/**
	 * @return the expand
	 */
	public String getExpand() {
		return expand;
	}

	/**
	 * @param expand the expand to set
	 */
	public void setExpand(String expand) {
		this.expand = expand;
	}

	@Override
	public String toString() {
		return "TaskConfig [id=" + id + ", status=" + status + "]";
	}
}