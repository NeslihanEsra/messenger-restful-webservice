package com.nea.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import com.nea.ws.database.DatabaseOperaitons;
import com.nea.ws.model.Profile;

@Path("/profiles")
public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseOperaitons.getProfiles();
	
	public ProfileService() {
		profiles.put("nea", new Profile(1L, "esra", "altın", "nea"));
		profiles.put("mı", new Profile(2L, "merve", "ışık", "mı"));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId((long)profiles.size() + 1);
		return profiles.put(profile.getProfileName(), profile);
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		return profiles.put(profile.getProfileName(), profile);
	}
	
	public void removeProfile(String profileName) {
		profiles.remove(profileName);
	}

}
