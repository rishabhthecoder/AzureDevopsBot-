package com.example.pepsico.addNewStory.user;

import java.util.List;

public class UserStory {
	private Long id;//ID -> witc_2_txt
	private String teamProject;
	public String getTeamProject() {
		return teamProject;
	}
	public void setTeamProject(String teamProject) {
		this.teamProject = teamProject;
	}
	private String title;//	Title -> witc_1_txt
	private String State;//State->witc_3_txt
	private int storyPoint;//Story point -> witc_12_txt
	private String iterationPath;//	Iteration Path-> witc_6_txt
	private List<String> tags;//Tags-> 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public int getStoryPoint() {
		return storyPoint;
	}
	public void setStoryPoint(int storyPoint) {
		this.storyPoint = storyPoint;
	}
	public String getIterationPath() {
		return iterationPath;
	}
	public void setIterationPath(String iterationPath) {
		this.iterationPath = iterationPath;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	protected UserStory(Long id, String title, String state, int storyPoint, String iterationPath,
			List<String> tags) {
		super();
		this.id = id;
		this.title = title;
		State = state;
		this.storyPoint = storyPoint;
		this.iterationPath = iterationPath;
		this.tags = tags;
	}
	public UserStory() {
		// TODO Auto-generated constructor stub
	}
	
}
