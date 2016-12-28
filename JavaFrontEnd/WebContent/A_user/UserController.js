app.controller('UserController', function($scope, $rootScope, $location,
		UserService) {
	$scope.users;
	$scope.user = {
		userid : '',
		role : '',
		dateofbirth : '',
		fullname : '',
		gender : '',
		mobileno : '',
		email : '',
		username : '',
		password : '',
		city : '',
		status : '',
		isOnline : '',
		enabled : ''
	};
	$scope.message;
	$scope.errorMessage;

	function fetchAllUsers() {
		console.log('entering fetchall users in user controller')
		UserService.fetchAllUsers().then(function(d) {
			$scope.users = d;
		}, function(error) {
			console.log(error);
		})
	}
	fetchAllUsers();

	$scope.submit = function() {
		console.log('Entering - submit function in usercontroller')
		UserService.authenticate($scope.user).then(function(response) {
			$scope.user = response.data;
			$rootScope.currentUser = $scope.user;
			fetchAllUsers();
			$location.path("/home");
		}, function(response) {// invalid user name and password - failure
			console.log('invalid username and password')
			$scope.message = "Invalid Username and Password";
			$location.path("/login");
		})

	}

	$rootScope.logout = function() {
		console.log('logout function')
		delete $rootScope.currentUser;

		UserService.logout().then(function(response) {
			console.log("logged out successfully..");
			$scope.message = "Logged out Successfully";
			$location.path('/home')
		}, function(response) {
			console.log(response.status);
		})
	}

	$scope.registerUser = function() {
		console.log('entering registerUser')
		$scope.user.isOnline = false;
		$scope.user.status = 'P';
		$scope.user.enabled = false;

		UserService.registerUser($scope.user).then(
				function(response) { // success
					console.log("registration success" + response.status)
					$location.path("/home");
				},
				function(response) {// failure
					console.log("registration failed" + response.status)
					console.log(response.data)
					$scope.errorMessage = "Registration failed...."
							+ response.data.message
					$location.path("/register")
				})
	}

	$scope.deleteUser = function(id) {
		console.log("entering delete user in controller with id " + id)
		UserService.deleteUser(id).then(function(d) {
			console.log('deleted successfully')
			console.log(d)
			fetchAllUsers();
			$location.path('/listUsers')
		}, function() {
			console.log("unable to delete the record")
		})

	}

})
