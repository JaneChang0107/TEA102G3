package com.websocketchat.controller;

import com.member.model.MemberVO;

public class ChatroomVO implements java.io.Serializable {

	private MemberVO member;

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}

	public ChatroomVO(MemberVO member) {
		super();
		this.member = member;
	}
	

}
