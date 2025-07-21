package com.MyAzienda.SpringPhotos.controllers.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.MyAzienda.SpringPhotos.models.Photo;

@RestController
@RequestMapping("admin/api/photos")
public class AdminPhotoController {
	
	private List<Photo> list;
	private int lastId;
	
	public AdminPhotoController() {
		list = new ArrayList<Photo>();

		list.add(new Photo(1,"./img/01.png"));
		list.add(new Photo(2,"./img/02.png"));
		list.add(new Photo(3,"./img/03.png"));
		
		lastId = 3;
	}
	
	@GetMapping
	public Iterable<Photo> getAll() {
		return list;
	}
	
	@GetMapping("{id}")
	public Photo getById(@PathVariable int id) {
		Optional<Photo> photo = ((Collection<Photo>) getAll())
														.stream()
														.filter(ph -> ph.getId() == id)
														.findFirst();
		
		if (photo.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "photo id = " + id + " not found");
		}
		return photo.get();
	}
	
//	@RequestMapping(value = "admin/api/photos", method = RequestMethod.POST)
	@PostMapping
	public Photo create(@RequestBody Photo photo) {
		lastId++;
		photo.setId(lastId);
		list.add(photo);
		return photo;
	}

	@PutMapping("{id}")
	public Photo update(@PathVariable int id, @RequestBody Photo photo) {
		Optional<Photo> foundPhoto = list.stream().filter(ph -> ph.getId() == id).findFirst();

		if (foundPhoto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "photo id = " + id + " not found");
		}
		foundPhoto.get().setUrl(photo.getUrl());
		return foundPhoto.get();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		Optional<Photo> foundPhoto = list.stream().filter(ph -> ph.getId() == id).findFirst();
		
		if (foundPhoto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "photo id = " + id + " not found");
		}
		
		list.remove(foundPhoto.get());
	}
}
