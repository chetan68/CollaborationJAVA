var app = angular.module("myApp", [ 'ngRoute' ])
app.config(function($routeProvider) {
	console.log('entering configuration')
	$routeProvider

	.when('/login', {
		controller : 'UserController',
		templateUrl : 'A_user/login.html'
	})

	.when('/home', {
		templateUrl : 'A_home/home.html'
	})

	.when('/register', {
		controller : 'UserController',
		templateUrl : 'A_user/register.html'

	}).when('/listUsers', {
		controller : 'UserController',
		templateUrl : 'A_user/listUsers.html'
	})

	.when('/edit', {
		controller : 'EditUserController',
		templateUrl : 'A_user/editUsers.html'
	})

	.when('/postJob', {
		controller : 'JobController',
		templateUrl : 'A_job/createJob.html'
	})

	.when('/listJobs', {
		controller : 'JobController',
		templateUrl : 'A_job/listJobs.html'
	})

	.when('/listaJobs', {
		controller : 'JobController',
		templateUrl : 'A_job/listJobs.html'

	})
	
	.when('/listpJobs', {
		controller : 'JobController',
		templateUrl : 'A_job/listJobs.html'
	})

	.when('/listrJobs', {
		controller : 'JobController',
		templateUrl : 'A_job/listJobs.html'
	})

	.when('/editJob', {
		controller : 'EditJobController',
		templateUrl : 'A_job/editJob.html'
	})

})