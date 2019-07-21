package com.eksad.miniproject.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum Position {
	DEVELOPER,
	SCRUM_MASTER,
	TEAM_LEAD,
	IT_MANAGER,
	CTO
	
//	public final String label;
//	
//	private Position(String label) {
//		this.label = label;
//	}

}
