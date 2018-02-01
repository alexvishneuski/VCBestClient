# VKLayouts
Mastering  layouts makeup

common authorisation schema
-> App version checking -> VKAuth -> App

1. App version checking
 1.1 if (online): version checking  -> VKAuth
 1.2 else
 (starts service
 that expects access to the Internet
 for one-time version checking)     -> VKAuth

2. VKAuth
 2.1 if (online): VKAuth  -> App
 2.2 else
 (starts service
 that expects access to the Internet
 for one-time VKAuth)     -> App
======================================

VKAuth schema
1. validate token from shared preferences
1.1 getting from shared preferences
1.2 validate:
            true -> app
            false ->2.

2. refresh token
2.1 getting from Api
2.2 saving to shared preferences
2.3 refreshing tokenholder

3. if an error occurred because the token is invalid -> 2.
========================================

 util modules:
 1. access token holder
 2. ifOnline checker


<p align="center">
  <img src="vkbestclient\src\main\res\assets\dialogs.jpg" width="350"/>

</p>
