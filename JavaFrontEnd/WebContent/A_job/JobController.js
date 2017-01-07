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
		expirydate : ''
	}

	/*
	 * $scope.saveJob = function() { console.log('enteringsave job in job
	 * controller')
	 * 
	 * JobService.saveJob($scope.job).then(function(response) {
	 * console.log("successfully inserted job details"); alert("Posted job
	 * successfully"); $location.path('/home'); }, function(response) {
	 * console.log("failure " + response.status);
	 * console.log(response.data.message) $location.path('/postJob') }) }
	 */

	$scope.saveJob = function() {
		console.log('enteringsave job in job controller')
		console.log($scope.job.postdate)
		console.log($scope.job.expirydate)
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

	/*
	 * getDistinctJobStatus function getDistinctJobStatus() {
	 * console.log('entering get all distinct job status')
	 * JobService.getDistinctJobStatus().then(function(response) {
	 * console.log(response.status); $scope.statuslist = response.data; },
	 * function(response) { console.log(response.status) }) }
	 * getDistinctJobStatus();
	 * 
	 * 
	 * function getJobsByStatus(id) { console.log('entering get all jobs by
	 * status') JobService.getJobsByStatus(id).then(function(response) {
	 * $scope.jobsbysta = response.data; console.log("response.status");
	 * //$location.path("/listJobsAd") }, function(response) {
	 * console.log(response.status) }) } getJobsByStatus("P");
	 */

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

})
