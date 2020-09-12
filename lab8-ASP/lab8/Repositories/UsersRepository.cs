using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using lab8.Models;

namespace lab8.Repositories
{
    public class UsersRepository
    {

        public User GetUser(string userName, string userPassword)
        {
            SqlConnection DbConnection = new SqlConnection(ConfigurationManager.ConnectionStrings["connect"].ConnectionString);
            User user = null;
            using (DbConnection)
            {
                var searchCommand =
                    new SqlCommand("select * from users where user_name = @Username and user_password = @Password;",
                        DbConnection);
                searchCommand.Parameters.AddWithValue("@Username", userName);
                searchCommand.Parameters.AddWithValue("@Password", userPassword);

                DbConnection.Open();
                var dataReader = searchCommand.ExecuteReader();
                if (dataReader.HasRows)
                {
                    dataReader.Read();
                    var aux = dataReader.GetName(0);
                    user = new User
                    {
                        Id = Convert.ToInt32(dataReader["id"].ToString()),
                        UserName = dataReader["user_name"].ToString(),
                        UserPassword = dataReader["user_password"].ToString()
                    };
                    dataReader.Close();
                    DbConnection.Close();
                }
            }

            return user;
        }

        public Boolean RegisterUser(string userName, string userPassword)
        {
            SqlConnection DbConnection = new SqlConnection(ConfigurationManager.ConnectionStrings["connect"].ConnectionString);
            using (DbConnection)
            {
                var insertCommand =
                    new SqlCommand("insert into users(user_name, user_password) values(@Username, @Password);",
                        DbConnection);
                insertCommand.Parameters.AddWithValue("@Username", userName);
                insertCommand.Parameters.AddWithValue("@Password", userPassword);
                DbConnection.Open();
                insertCommand.ExecuteNonQuery();
            }
            DbConnection.Close();
            User user = GetUser(userName, userPassword);
            if (user != null)
            {
                return true;
            }

            return false;
        }
    }
}