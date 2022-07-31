document.ready = function () {
    alert('Hello World!');
    var year;
}

function getYear(date) {
    var a = date.split('-');
    return a[0];
}