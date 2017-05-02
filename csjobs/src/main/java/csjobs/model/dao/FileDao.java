package csjobs.model.dao;

import csjobs.model.File;

public interface FileDao {

	File saveFile(File file);
	File getFile(Long id);
}
