package org.tapp.dal;

import java.util.ArrayList;

import org.tapp.bll.Document;

public interface InjectionDAOInter {

	public ArrayList<Document> Listedocs();

	public void ajoutdoc(String nom);

	public Document selectdoc(String nom);

}
