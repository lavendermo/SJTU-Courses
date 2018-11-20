var column_margin_z = { top: 20, right: 20, bottom: 30, left: 40 },
    column_width_z = 960 - column_margin_z.left - column_margin_z.right,
    column_height_z = 500 - column_margin_z.top - column_margin_z.bottom;

//var formatPercent_z = d3.format(".0%");

var column_x_z = d3.scale.ordinal()
    .rangeRoundBands([0, column_width_z], .1, 1);

var column_y_z = d3.scale.sqrt()  //开根号
    .range([column_height_z, 0]);

var column_xAxis_z = d3.svg.axis()
    .scale(column_x_z)
    .orient("bottom");

var column_yAxis_z = d3.svg.axis()
    .scale(column_y_z)
    .orient("left");
//.tickFormat(formatPercent_z);

var column_svg_z = d3.select("body").append("svg")
    .attr("width", column_width_z + column_margin_z.left + column_margin_z.right)
    .attr("height", column_height_z + column_margin_z.top + column_margin_z.bottom)
    .append("g")
    .attr("transform", "translate(" + 1.5*column_margin_z.left + "," + column_margin_z.top + ")");
var column_message_data = [
    { districtName: "朝阳区", districtNum: 1686514 },
    { districtName: "东城区", districtNum: 218037 },
    { districtName: "海淀区", districtNum: 280793 },
    { districtName: "西城区", districtNum: 117180 },
    { districtName: "通州区", districtNum: 160014 },
    { districtName: "丰台区", districtNum: 540060 },
    { districtName: "大兴区", districtNum: 166274 },
    { districtName: "昌平区", districtNum: 69766 },
    { districtName: "房山区", districtNum: 56403 },
    { districtName: "石景山区", districtNum: 31144 },
    { districtName: "顺义区", districtNum: 16782 },
    { districtName: "怀柔区", districtNum: 3502 },
    { districtName: "门头沟区", districtNum: 5757 },
    { districtName: "延庆县", districtNum: 3530 },
    { districtName: "密云县", districtNum: 1840 },
    { districtName: "平谷区", districtNum: 790 }
];
var column_sum_z=0;
for(var i=0;i<column_message_data.length;++i)
  column_sum_z=column_sum_z+column_message_data[i]["districtNum"];
//console.log(column_sum_z);
/*column_message_data.forEach(function(d) {
    d.frequency = +d.frequency;
});*/

column_x_z.domain(column_message_data.map(function(d) { return d.districtName; }));
column_y_z.domain([0, d3.max(column_message_data, function(d) { return d.districtNum; })]);

column_svg_z.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0," + column_height_z + ")")
    .call(column_xAxis_z);

column_svg_z.append("g")
    .attr("class", "y axis")
    .call(column_yAxis_z)
    .append("text")
    .attr("transform", "rotate(-90)")
    .attr("y", 6)
    .attr("dy", ".71em")
    .style("text-anchor", "end")
    .text("Message Num");

column_svg_z.selectAll(".bar")
    .data(column_message_data)
    .enter().append("rect")
    .attr("class", "bar")
    .attr("x", function(d) { return column_x_z(d.districtNum); })
    .attr("width", column_x_z.rangeBand())
    .attr("y", function(d) { return column_y_z(d.districtNum); })
    .attr("height", function(d) { return column_height_z - column_y_z(d.districtNum); })
    .append("title")
    .text(function(d){
      //console.log(d);
      return d["districtNum"];
    });
    /*.on("mouseover",function(){
      d3.select(this)
      .attr("flll","red")
    })
    .on("mouseout",function(){
      d3.select(this)
      .attr("fill","steelblue")
    });*/
d3.select("input").on("change", change);

var sortTimeout = setTimeout(function() {
    d3.select("input").property("checked", true).each(change);
}, 2000);

function change() {
    clearTimeout(sortTimeout);

    // Copy-on-write since tweens are evaluated after a delay.
    var x0 = column_x_z.domain(column_message_data.sort(this.checked ?
                function(a, b) { return b.districtNum - a.districtNum; } :
                function(a, b) { return d3.ascending(a.districtName, b.districtName); })
            .map(function(d) { return d.districtName; }))
        .copy();

    column_svg_z.selectAll(".bar")
        .sort(function(a, b) { return x0(a.districtName) - x0(b.districtName); });

    var transition = column_svg_z.transition().duration(750),
        delay = function(d, i) { return i * 50; };

    transition.selectAll(".bar")
        .delay(delay)
        .attr("x", function(d) { return x0(d.districtName); });

    transition.select(".x.axis")
        .call(column_xAxis_z)
        .selectAll("g")
        .delay(delay);
};