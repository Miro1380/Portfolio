
superuser that was created  username:admin1 pw: 1234pass 
Once super user is created, you can alter and book tables / items
Prior to that, there should be a 401 error.

If you're using Insomnia or Postman use basic auth with username and passwords.

#To create Token:
http://127.0.0.1:8000/auth/token/login

http://127.0.0.1:8000/auth/users/
http://127.0.0.1:8000/auth/users/login/
http://127.0.0.1:8000/restaurant/
http://127.0.0.1:8000/restaurant/menu/
http://127.0.0.1:8000/restaurant/booking/tables/
