/**
 * ViewAlbumPresentationModel.java
 * 10 Oct 2011 Copyright Cheng Wei and Robert Taylor
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package robobinding.sample.presentationmodel;

import robobinding.presentationmodel.AbstractPresentationModel;
import robobinding.sample.CreateEditAlbumActivity;
import robobinding.sample.dao.AlbumDao;
import robobinding.sample.model.Album;
import android.content.Context;
import android.content.Intent;

/**
 * 
 * @since 1.0
 * @author Cheng Wei
 * @author Robert Taylor
 */
public class ViewAlbumPresentationModel extends AbstractPresentationModel
{
	private final Context context;
	private final AlbumDao albumDao;
	private final long albumId;
	private Album album;
	
	public ViewAlbumPresentationModel(Context context, AlbumDao albumDao, long albumId)
	{
		this.context = context;
		this.albumDao = albumDao;
		this.albumId = albumId;
		this.album = albumDao.get(albumId);
	}

	public String getTitle()
	{
		return album.getTitle();
	}
	
	public String getArtist()
	{
		return album.getArtist();
	}
	
	public String getComposer()
	{
		return album.getComposer();
	}
	
	public boolean isComposerEnabled()
	{
		return album.isClassical();
	}
	
	public String getClassicalDescription()
	{
		return album.isClassical() ? "Classical" : "Not classical";
	}
	
	public void editAlbum()
	{
		Intent intent = new Intent(context, CreateEditAlbumActivity.class);
		intent.putExtra(CreateEditAlbumActivity.ALBUM_ID, album.getId());
		context.startActivity(intent);
	}

	public void refresh()
	{
		String oldTitle = getTitle();
		String oldArtist = getArtist();
		String oldComposer = getComposer();
		String oldClassicalDescription = getClassicalDescription();
		
		this.album = albumDao.get(albumId);
		
		firePropertyChange("title", oldTitle, getTitle());
		firePropertyChange("artist", oldArtist, getArtist());
		firePropertyChange("composer", oldComposer, getComposer());
		firePropertyChange("classicalDescription", oldClassicalDescription, getClassicalDescription());
	}
}
