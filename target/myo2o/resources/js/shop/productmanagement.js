$(function() {
	// 获取此店铺下的商品列表的url
	var listUrl = '/myo2o/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999';
	// 商品下架url
	var statusUrl = '/myo2o/shopadmin/modifyproduct';

	getList();

	function getList() {
		$
				.getJSON(
						listUrl,
						function(data) {
							if (data.success) {
								var dataList = data.productList;
								var tempHtml = '';
								dataList
										.map(function(item, index) {
											var textOp = "下架";
											var contraryStatus = 0;
											if (item.enableStatus == 0) {
												// 状态为0的是已下架商品，操作变为上架
												textOp = "上架";
												contraryStatus = 1;
											} else {
												contraryStatus = 0;
											}
											// 拼接每件商品的行信息
											tempHtml += ''
													+ '<div class="row row-product now"><div class="col-33">'
													+ item.productName
													+ '</div><div class="col-20">'
													+ item.priority
													+ '</div><div class="col-40">'
													+ '<a href="#" class="edit" data-id="'
													+ item.productId
													+ '" data-status="'
													+ item.enableStatus
													+ '">编辑</a>'
													+ '<a href="#" class="status" data-id="'
													+ item.productId
													+ '" data-status="'
													+ contraryStatus
													+ '">'
													+ textOp
													+ '</a>'
													+ '<a href="#" class="preview" data-id="'
													+ item.productId
													+ '" data-status="'
													+ item.enableStatus
													+ '">预览</a>'
													+ '</div>'
													+ '</div>';
										});
								$('.product-wrap').html(tempHtml);
							}
						});
	}

	$('.product-wrap')
			.on(
					'click',
					'a',
					function(e) {
						var target = $(e.currentTarget);
						if (target.hasClass('edit')) {
							// 若a标签有class edit则进入店铺信息编辑页面，并带productId参数
							window.location.href = '/myo2o/shopadmin/productoperation?productId='
									+ e.currentTarget.dataset.id;
						} else if (target.hasClass('status')) {
							// 若a标签有class status则调用后台功能上/下架相关商品，并带有productId参数
							changeItemStatus(e.currentTarget.dataset.id,
									e.currentTarget.dataset.status);
						} else if (target.hasClass('preview')) {
							// 若a标签有class preview则去前台展示系统预览商品情况
							window.location.href = '/myo2o/frontennd/productdetail?productId='
									+ e.currentTarget.dataset.id;
						}
					});

	function changeItemStatus(id, enableStatus) {
		// 定义product json对象并添加productId以及状态(上架/下架)
		var product = {};
		product.productId = id;
		product.enableStatus = enableStatus;
		$.confirm("确定么?", function() {
			// 上下架相关商品
			$.ajax({
				url : statusUrl,
				type : 'POST',
				data : {
					productStr : JSON.stringify(product),
					statusChange : true
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.toast('操作成功!');
						getList();
					} else {
						$.toast('操作成功!');
					}
				}
			});
		});
	}

});