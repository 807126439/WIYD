package com.wb.web.portals.dto.themeActivity;

import java.util.List;

import com.wb.web.portals.dto.banChunk.BanChunkDTO;


public class CityBuilderActivity {
	private ThemeActivityDTO activity;			//当前活动信息
	private List<BanChunkDTO> banChunkList;		//当前活动包含的板块信息
	
	
	
	
	public ThemeActivityDTO getActivity() {
		return activity;
	}
	public void setActivity(ThemeActivityDTO activity) {
		this.activity = activity;
	}
	public List<BanChunkDTO> getBanChunkList() {
		return banChunkList;
	}
	public void setBanChunkList(List<BanChunkDTO> banChunkList) {
		this.banChunkList = banChunkList;
	}
	
	
	
}
