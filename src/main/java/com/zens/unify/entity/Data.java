package com.zens.unify.entity;

public class Data {
	
    public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data(Long taskConfigId) {
		super();
		this.taskConfigId = taskConfigId;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data.ID
     *
     * @mbggenerated Wed Jul 16 11:37:55 CST 2014
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data.FTaskConfigId
     *
     * @mbggenerated Wed Jul 16 11:37:55 CST 2014
     */
    private Long taskConfigId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data.FData
     *
     * @mbggenerated Wed Jul 16 11:37:55 CST 2014
     */
    private String data;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data.FUpdateIp
     *
     * @mbggenerated Wed Jul 16 11:37:55 CST 2014
     */
    private String updateIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data.FUpdateTime
     *
     * @mbggenerated Wed Jul 16 11:37:55 CST 2014
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data.FCity
     *
     * @mbggenerated Wed Jul 16 11:37:55 CST 2014
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data.FExpand
     *
     * @mbggenerated Wed Jul 16 11:37:55 CST 2014
     */
    private String expand;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_data.FBackupData
     *
     * @mbggenerated Wed Jul 16 11:37:55 CST 2014
     */
    private String backupData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaskConfigId() {
		return taskConfigId;
	}

	public void setTaskConfigId(Long taskConfigId) {
		this.taskConfigId = taskConfigId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUpdateIp() {
		return updateIp;
	}

	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public String getBackupData() {
		return backupData;
	}

	public void setBackupData(String backupData) {
		this.backupData = backupData;
	}

}