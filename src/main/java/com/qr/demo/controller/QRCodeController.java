package com.qr.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qr.demo.service.QRCodeService;

@RestController
public class QRCodeController {

	@Autowired
	private QRCodeService qrCodeService; 

	@RequestMapping("/QRCodeGenerator/{value}")
	public String qRCodeGenerator(@PathVariable("value") String value){
		String filename  = qrCodeService.generator(value);
		System.out.println(filename);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/qr/")
                .path(filename)
                .path(".png")
                .toUriString();
        
		return fileDownloadUri.toString();
	}
	
	@RequestMapping("/QRCodeReader/{id}")
	public String qRCodeReader(@PathVariable("id") String id){
		String value =  qrCodeService.read(id);
		return value;
	}
}
