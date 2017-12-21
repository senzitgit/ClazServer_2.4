package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface NoteDao 
{

	Integer insertStudentClazNote(Note noteObj);

	List<String> getClazNotes(String userId, int clazEventDetailId);
	
}
