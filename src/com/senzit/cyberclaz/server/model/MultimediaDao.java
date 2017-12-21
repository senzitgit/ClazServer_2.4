package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface MultimediaDao {

	List<Object[]> getAttachmentDetailsUploadedViaPortal(String teacherId);

	Integer saveAttachmentDetailsUploadFromPortal(MultimediaLibrary ml);

	void deleteAttachmentDetailsUploadFromPortal(MultimediaLibrary ml);

}
