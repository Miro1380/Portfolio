from rest_framework import BasePermission

class IsManagerorAdmin(BasePermission):
    def has_permission(self,request,view):
        user = request.user
        return user.is_authenticated