var app = angular.module("myApp", [ 'ngRoute' ])
app.config(function($routeProvider) {
	console.log('entering configuration')
	$routeProvider

	.when('/login', {
		controller : 'UserController',
		templateUrl : 'A_user/login.html'
	}).when('/home', {
		templateUrl : 'A_home/home.html'
	}).when('/register', {
		controller : 'UserController',
		templateUrl : 'A_user/register.html'

	}).when('/listUsers', {
		controller : 'UserController',
		templateUrl : 'A_user/listUsers.html'
	}).when('/edit', {
		controller : 'EditUserController',
		templateUrl : 'A_user/editUsers.html'
	}).when('/postJob', {
		controller : 'JobController',
		templateUrl : 'A_job/createJob.html'
	})

	.when('/listJobs', {
		controller : 'JobController',
		templateUrl : 'A_job/listJobs.html'
	})

	.when('/listJobsAd', {
		controller : 'JobController',
		templateUrl : 'A_job/listJobsBySta.html'

	})

	.when('/editJob', {
		controller : 'EditJobController',
		templateUrl : 'A_job/editJob.html'

	})

	.when('/uploadPicture', {
		templateUrl : 'A_user/uploadPicture.html'
	})

	.when('/createblog', {
		controller : 'BlogController',
		templateUrl : 'A_blog/createBlog.html'
	}).when('/listBlogs', {
		controller : 'BlogController',
		templateUrl : 'A_blog/listBlog.html'
	}).when('/editBlog', {
		controller : 'EditBlogController',
		templateUrl : 'A_blog/editBlog.html'

	}).when('/createnews', {
		controller : 'NewsController',
		templateUrl : 'A_news/createNews.html'
	}).when('/listNews', {
		controller : 'NewsController',
		templateUrl : 'A_news/listNews.html'

	}).when('/editNews', {
		controller : 'EditNewsController',
		templateUrl : 'A_news/editNews.html'

	}).when('/postEvent', {
		controller : 'EventController',
		templateUrl : 'A_event/createEvent.html'
	}).when('/listEvents', {
		controller : 'EventController',
		templateUrl : 'A_event/listEvent.html'

	}).when('/editEvent', {
		controller : 'EditEventController',
		templateUrl : 'A_event/editEvent.html'

	}).when('/friendsList', {
		controller : 'FriendController',
		templateUrl : 'A_friend/listOfFriends.html'
	}).when('/pendingRequest', {
		controller : 'FriendController',
		templateUrl : 'A_friend/pendingRequest.html'
	}).when('/getAllUsers', {
		controller : 'UserController',
		templateUrl : 'A_user/listOfUsers.html'
	}).when('/chat', {
		controller : 'ChatController',
		templateUrl : 'A_chat/chatView.html'
	})
})