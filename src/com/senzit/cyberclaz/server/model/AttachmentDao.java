package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface AttachmentDao {

	Integer saveAttachment(Attachment attachmentObj);

	List<Object[]> getAttachmentLink(int clazEventDetailId);

	Integer saveImageDetails(String fileName,int classEventDetailId,byte imageType,String name,String time,String type);

	Integer saveImageDetailsFromPortal(String name, int classEventDetailId,
			byte imageType,String link, String time, String type);
}
