app.controller('JobController', function($scope, $rootScope, $location,
		JobService) {
	$scope.job = {
		jobid : '',
		title : '',
		description : '',
		postdate : '',
		qualification : '',
		salary : '',
		location : '',
		experience : '',
		companyname : '',
		status : '',
		validity : '',
		expirydate : ''

	}
	$scope.saveJob = function() {
		console.log('enteringsave job in job controller')

		JobService.saveJob($scope.job).then(function(response) {
			console.log("successfully inserted job details");
			alert("Posted job successfully");
			$location.path('/home');
		}, function(response) {
			console.log("failure " + response.status);
			console.log(response.data.message)
			$location.path('/postJob')
		})
	}

	function getAllJobs() {
		console.log('entering get all jobs')
		JobService.getAllJobs().then(function(response) {
			console.log(response.status);
			$scope.jobs = response.data;

		}, function(response) {
			console.log(response.status)
		})

	}
	getAllJobs();

	$scope.deleteJob = function(id) {
		console.log("entering delete job in controller with id " + id)
		JobService.deletejob(id).then(function(d) {
			console.log('deleted successfully')
			console.log(d)
			getAllJobs();
			$location.path('/listJobs')
		}, function() {
			console.log("unable to delete the record")
		})
	}

	$scope.listApprovedJobs = function() {
		console.log("entering list approved jobs in  jobcontroller")
		JobService.listApprovedJobs("A").then(function(d) {
			console.log('fetched approved jobs successfully')
			console.log(d)
			$location.path('/listJobs')
		}, function() {
			console.log("unable to delete the record")
		})
	}

})
