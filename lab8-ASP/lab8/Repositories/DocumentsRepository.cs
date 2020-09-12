using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using lab8.Models;

namespace lab8.Repositories
{
    public class DocumentsRepository
    {
        public SqlConnection DbConnection =
            new SqlConnection(ConfigurationManager.ConnectionStrings["connect"].ConnectionString);

        public List<Document> GetAllDocuments()
        {
            var getAllCommand = new SqlCommand("select * from documents;", DbConnection);
            DbConnection.Open();
            var dataReader = getAllCommand.ExecuteReader();
            List<Document> documentsList = new List<Document>();
            if (dataReader.HasRows)
            {
                while (dataReader.Read())
                {
                    Document document
                        = new Document
                        {
                            Id = Convert.ToInt32(dataReader["id"].ToString()),
                            Author = dataReader["author"].ToString(),
                            Title = dataReader["title"].ToString(),
                            NumberPages = Convert.ToInt32(dataReader["numberPages"]),
                            Format = dataReader["format"].ToString(),
                            Type = dataReader["type"].ToString()
                        };
                    documentsList.Add(document);
                }

                dataReader.Close();
                DbConnection.Close();
            }

            return documentsList;
        }


        public void SaveDocument(Document document)
        {
            using (DbConnection)
            {
                var insertCommand =
                    new SqlCommand(
                        "insert into documents(author, title, numberPages, format, type) values (@Author, @Title, @NumberPages, @Format, @Type);",
                        DbConnection);
                insertCommand.Parameters.AddWithValue("@Author", document.Author);
                insertCommand.Parameters.AddWithValue("@Title", document.Title);
                insertCommand.Parameters.AddWithValue("@NumberPages", document.NumberPages);
                insertCommand.Parameters.AddWithValue("@Format", document.Format);
                insertCommand.Parameters.AddWithValue("@Type", document.Type);
                DbConnection.Open();
                insertCommand.ExecuteNonQuery();
            }
        }


        public void DeleteDocument(int id)
        {
            using (DbConnection)
            {
                var insertCommand =
                    new SqlCommand(
                        "delete from documents where id=@Id",
                        DbConnection);
                insertCommand.Parameters.AddWithValue("@Id", id);
                DbConnection.Open();
                insertCommand.ExecuteNonQuery();
            }
        }

        public void UpdateDocument(int id, Document document)
        {
            using (DbConnection)
            {
                var updateCommand =
                    new SqlCommand(
                        "update documents set author=@Author, title=@Title, numberPages=@NumberPages, format=@Format, type=@Type where id=@Id;",
                        DbConnection);
                updateCommand.Parameters.AddWithValue("@Author", document.Author);
                updateCommand.Parameters.AddWithValue("@Title", document.Title);
                updateCommand.Parameters.AddWithValue("@NumberPages", document.NumberPages);
                updateCommand.Parameters.AddWithValue("@Format", document.Format);
                updateCommand.Parameters.AddWithValue("@Type", document.Type);
                updateCommand.Parameters.AddWithValue("@Id", id);
                DbConnection.Open();
                updateCommand.ExecuteNonQuery();
            }
        }

        public List<Document> GetAllFiltered(string type)
        {
            var getAllFiltered = new SqlCommand("select * from documents where type=@Type;", DbConnection);
            getAllFiltered.Parameters.AddWithValue("@Type", type);
            DbConnection.Open();
            var dataReader = getAllFiltered.ExecuteReader();
            List<Document> documentsList = new List<Document>();
            if (dataReader.HasRows)
            {
                while (dataReader.Read())
                {
                    Document document
                        = new Document
                        {
                            Id = Convert.ToInt32(dataReader["id"].ToString()),
                            Author = dataReader["author"].ToString(),
                            Title = dataReader["title"].ToString(),
                            NumberPages = Convert.ToInt32(dataReader["numberPages"]),
                            Format = dataReader["format"].ToString(),
                            Type = dataReader["type"].ToString()
                        };
                    documentsList.Add(document);
                }

                dataReader.Close();
                DbConnection.Close();
            }

            return documentsList;
        }
    }
}