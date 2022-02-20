package com.ieszv.progmulti.proyectofloraad.model.Album;

import java.io.File;

public abstract class AlbumStorageDirFactory {
	public abstract File getAlbumStorageDir(String albumName);
}
