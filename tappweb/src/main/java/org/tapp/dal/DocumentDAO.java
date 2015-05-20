package org.tapp.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.inject.Model;

import org.tapp.bll.Document;

@Model
public class DocumentDAO extends ServicesDAO {
	public static final String INSERT_DOCUMENTS = "INSERT INTO documents (nom_documents) VALUES(?)";
	public static final String SELECT_DOCUMENTS = "SELECT * FROM documents";
	public static final String SELECT_DOCUMENT_PAR_NOM = "SELECT * FROM documents WHERE nom_documents= ?";
	public static final String UPDATE_DOCUMENTS = "UPDATE documents SET nom_documents = ? WHERE nom_documents = ?";
	public static final String DELETE_DOCUMENTS = "DELETE FROM documents WHERE nom_documents = ?";

	public Document adddoc(String nom) throws SQLException {
		final PreparedStatement preparedStatement = ServicesDAO
				.preparedStatement(INSERT_DOCUMENTS);
		preparedStatement.setString(1, nom);
		preparedStatement.executeUpdate();

		return new Document(nom);
	}

	public Document modifydoc(String nominit, String nommodif)
			throws SQLException {
		final PreparedStatement prepareStatement = ServicesDAO
				.preparedStatement(UPDATE_DOCUMENTS);
		prepareStatement.setString(1, nommodif);
		prepareStatement.setString(2, nominit);
		prepareStatement.executeUpdate();
		return new Document(nommodif);

	}

	public Document selectdoc(String nom) throws SQLException {
		final PreparedStatement prepareStatement = ServicesDAO
				.preparedStatement(SELECT_DOCUMENT_PAR_NOM);
		prepareStatement.setString(1, nom);
		final ResultSet rst = prepareStatement.executeQuery();
		String nomfinal = null;
		while (rst.next()) {

			nomfinal = rst.getString("nom_documents");
		}

		return new Document(nomfinal);

	}

	public ArrayList<Document> alldocs() throws SQLException {
		ArrayList<Document> tab = new ArrayList<Document>();
		final PreparedStatement preparedStatement = ServicesDAO
				.preparedStatement(SELECT_DOCUMENTS);
		final ResultSet rst = preparedStatement.executeQuery();
		while (rst.next()) {
			String nom = rst.getString("nom_documents");

			tab.add(new Document(nom));
		}

		return tab;

	}

	public void deletedoc(String nom) throws SQLException {
		final PreparedStatement preparedStatement = ServicesDAO
				.preparedStatement(DELETE_DOCUMENTS);
		preparedStatement.setString(1, nom);
		preparedStatement.executeUpdate();
	}
}