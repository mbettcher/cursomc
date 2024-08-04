package br.com.cdm.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static List<Long> decodeLongList(String s) {
		
		String[] vetor = s.split(",");
		List<Long> lista = new ArrayList<>();
		
		for(int i=0; i<vetor.length; i++) {
			lista.add(Long.valueOf(vetor[i]));
		}
		
		return lista;
		
		/* return Arrays.asList(s.split(",")).stream().map(x -> Long.valueOf(x)).toList(); */
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.getMessage();
			return "";
		}
	}

}
