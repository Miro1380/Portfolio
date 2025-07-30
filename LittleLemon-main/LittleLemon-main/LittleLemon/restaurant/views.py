from django.shortcuts import render
from rest_framework import viewsets,generics
from .serializers import UserSerializer, MenuSerializer, BookingSerializer
from .models import User, Menu,Booking
from rest_framework.response import Response
from rest_framework.decorators import api_view,permission_classes
from rest_framework.permissions import IsAuthenticated

# Create your views here
def index(request):
    return render(request, 'index.html',{})


class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer(queryset)
    permission_classes = [IsAuthenticated]
    def list(self,request):
        return Response ()
    
    #MenuItemView
class MenuItemView (generics.ListCreateAPIView):
    queryset = Menu.objects.all()
    serializer_class = MenuSerializer
    permission_classes = [IsAuthenticated]
    
    #def get(self, request):
        #return Response()
    #def post(self,request):
        #return Response()

class SingleMenuItemView(generics.RetrieveUpdateAPIView, generics.DestroyAPIView):
    queryset = Menu.objects.all()
    serializer_class = MenuSerializer

  

class BookingViewSet(viewsets.ModelViewSet):
    queryset = Booking.objects.all()
    serializer_class = BookingSerializer
    permission_classes = [IsAuthenticated]
    
#@api_view
#@permission_classes
def msg(request):
    return Response ({"message": "This view is protected"})
