export const seededRandom = function (seed) {
    var m = 2**35 - 31;
    var a = 185852;
    var s = seed % m;
    return function () {
        return (s = s * a % m) / m;
    };
}

export const fetchAPI = function(date) {
    let result = [];
    //changed from date.getDate()
    let random = seededRandom(date.getDate());

    let j = 0;
    for(let i = 17; i <= 23; i++) {
        if(random() < 0.5) {
            result.push({id:j, value:i + ':00', label:i+':00'});
        }
        else if(random() < 0.5) {
            result.push({id:j, value:i + ':30', label:i+':30'});
        }
        j++;
    }
    return result;
};
export const submitAPI = function(formData) {
    return true;
};