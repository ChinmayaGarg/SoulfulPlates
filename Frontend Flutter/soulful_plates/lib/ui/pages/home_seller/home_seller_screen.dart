import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/app_icons.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../widgets/base_common_widget.dart';
import '../order_detail/order_detail_screen.dart';
import 'home_seller_controller.dart';

class HomeSellerScreen extends GetView<HomeSellerController>
    with BaseCommonWidget {
  const HomeSellerScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Soulful Plates",
          textAlign: TextAlign.center,
          style: TextStyle(
            color: Colors.green.shade800,
            fontSize: 25,
            fontWeight: FontWeight.bold,
          ),
        ),
        backgroundColor: Colors.white,
        leading: Padding(
          padding: const EdgeInsets.only(left: 10.0),
          child: Image.asset(AppIcons.appIcon),
        ),
        actions: [
          PopupMenuButton(
            iconColor: Colors.white,
            itemBuilder: (BuildContext context) => <PopupMenuEntry>[
              PopupMenuItem(
                child: ListTile(
                  leading: Icon(Icons.history),
                  title: Text('Transaction History'),
                  onTap: () {
                    Get.toNamed(transactionHistorySellerViewRoute);
                  },
                ),
              ),
              PopupMenuItem(
                child: ListTile(
                  leading: Icon(Icons.store),
                  title: Text('Store Details'),
                  onTap: () {
                    Get.toNamed(storeDetailsViewRoute);
                  },
                ),
              ),
            ],
          ),
        ],
      ),
      backgroundColor: Colors.white,
      body: SafeArea(
        child: getBody(context),
      ),
    );
  }

  Widget getBody(BuildContext context) {
    return ListView(
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            10.rVerticalSizedBox(),
            CardOne(),
            20.rVerticalSizedBox(),
            Row(
              children: [
                Expanded(
                  child: Divider(
                    color: Colors.black,
                    thickness: 1,
                  ),
                ),
                Padding(
                  padding: EdgeInsets.symmetric(horizontal: 10),
                  child: Text(
                    'ORDERS',
                    style: TextStyle(
                      fontSize: 25,
                      fontWeight: FontWeight.bold,
                      fontFamily: 'Cambria',
                    ),
                  ),
                ),
                Expanded(
                  child: Divider(
                    color: Colors.black,
                    thickness: 1,
                  ),
                ),
              ],
            ),
            20.rVerticalSizedBox(),
            CardTwo(),
            20.rVerticalSizedBox(),
            CardThree(),
            20.rVerticalSizedBox(),
            CardFour(),
          ],
        ).paddingAll16(),
      ],
    );
  }
}

class CardOne extends StatelessWidget {
  const CardOne({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      constraints: BoxConstraints(maxWidth: 400),
      child: Card(
        color: Colors.green.shade800,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
          side: BorderSide(
            color: Colors.black,
            width: 1,
          ),
        ),
        elevation: 3,
        child: Flex(
          direction: Axis.vertical,
          children: [
            Column(
              children: [
                Row(
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          'Earnings',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 30,
                            color: Colors.white,
                          ),
                        ),
                        8.rVerticalSizedBox(),
                      ],
                    )
                  ],
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            Column(
                              children: [
                                Text(
                                  'Amount',
                                  style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 16,
                                    color: Colors.white,
                                  ),
                                ),
                              ],
                            )
                          ],
                        ),
                        Row(
                          children: [
                            Column(
                              children: [
                                Text(
                                  '\$1000.00 CAD',
                                  style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 24,
                                    color: Colors.white,
                                  ),
                                ),
                              ],
                            )
                          ],
                        )
                      ],
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      children: [
                        Row(
                          children: [
                            Column(
                              children: [
                                Text(
                                  'Orders',
                                  style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 16,
                                    color: Colors.white,
                                  ),
                                ),
                              ],
                            )
                          ],
                        ),
                        Row(
                          children: [
                            Column(
                              children: [
                                Text(
                                  '#54',
                                  style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 24,
                                    color: Colors.white,
                                  ),
                                ),
                              ],
                            )
                          ],
                        )
                      ],
                    )
                  ],
                ),
              ],
            )
          ],
        ).paddingAll16(),
      ),
    );
  }
}

class CardTwo extends StatelessWidget {
  const CardTwo({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(12),
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
            side: BorderSide(
              color: Colors.green.shade800,
              width: 1,
            ),
          ),
          elevation: 2,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    children: [
                      Container(
                        padding: EdgeInsets.symmetric(
                          vertical: 8,
                          horizontal: 16,
                        ),
                        decoration: BoxDecoration(
                          color: Colors.orange.shade500,
                          borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(12),
                            bottomRight: Radius.circular(12),
                          ),
                        ),
                        child: Text(
                          'Food Preparing',
                          style: TextStyle(color: Colors.white),
                        ),
                      )
                    ],
                  ),
                  Column(
                    children: [
                      Row(
                        children: [
                          Column(
                            children: [
                              Text(
                                '5 mins left',
                                style: TextStyle(fontSize: 16),
                              ),
                            ],
                          ),
                          8.rHorizontalSizedBox(),
                          Column(
                            children: [
                              Icon(
                                Icons.hourglass_bottom,
                                color: Colors.grey,
                              ),
                            ],
                          ),
                        ],
                      )
                    ],
                  )
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Order ID',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      GestureDetector(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => OrderDetailScreen(),
                            ),
                          );
                        },
                        child: Text(
                          '2141',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                    ],
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Address',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        '123 Main St, City, Country',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                children: [
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement call driver functionality
                      },
                      icon: Icon(Icons.phone),
                      label: Text(
                        'Call Driver',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 2,
                      ),
                    ),
                  ),
                  12.rHorizontalSizedBox(),
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement track driver functionality
                      },
                      icon: Icon(Icons.my_location),
                      label: Text(
                        'Track Order',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 3,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ).paddingAll16(),
        ),
      ),
    );
  }
}

class CardThree extends StatelessWidget {
  const CardThree({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(12),
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
            side: BorderSide(
              color: Colors.green.shade800,
              width: 1,
            ),
          ),
          elevation: 2,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    children: [
                      Container(
                        padding: EdgeInsets.symmetric(
                          vertical: 8,
                          horizontal: 16,
                        ),
                        decoration: BoxDecoration(
                          color: Colors.purple.shade500,
                          borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(12),
                            bottomRight: Radius.circular(12),
                          ),
                        ),
                        child: Text(
                          'Out for Delivery',
                          style: TextStyle(color: Colors.white),
                        ),
                      )
                    ],
                  ),
                  Column(
                    children: [
                      Row(
                        children: [
                          Column(
                            children: [
                              Text(
                                '10 mins left',
                                style: TextStyle(fontSize: 16),
                              ),
                            ],
                          ),
                          8.rHorizontalSizedBox(),
                          Column(
                            children: [
                              Icon(
                                Icons.hourglass_bottom,
                                color: Colors.grey,
                              ),
                            ],
                          ),
                        ],
                      )
                    ],
                  )
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Order ID',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      GestureDetector(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => OrderDetailScreen(),
                            ),
                          );
                        },
                        child: Text(
                          '2231',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                    ],
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Address',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        '121 Yale St, City, Country',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                children: [
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement call driver functionality
                      },
                      icon: Icon(Icons.phone),
                      label: Text(
                        'Call Driver',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 2,
                      ),
                    ),
                  ),
                  12.rHorizontalSizedBox(),
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement track driver functionality
                      },
                      icon: Icon(Icons.my_location),
                      label: Text(
                        'Track Order',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 3,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ).paddingAll16(),
        ),
      ),
    );
  }
}

class CardFour extends StatelessWidget {
  const CardFour({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(12),
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
            side: BorderSide(
              color: Colors.green.shade800,
              width: 1,
            ),
          ),
          elevation: 2,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    children: [
                      Container(
                        padding: EdgeInsets.symmetric(
                          vertical: 8,
                          horizontal: 16,
                        ),
                        decoration: BoxDecoration(
                          color: Colors.purple.shade500,
                          borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(12),
                            bottomRight: Radius.circular(12),
                          ),
                        ),
                        child: Text(
                          'Out for Delivery',
                          style: TextStyle(color: Colors.white),
                        ),
                      )
                    ],
                  ),
                  Column(
                    children: [
                      Row(
                        children: [
                          Column(
                            children: [
                              Text(
                                '10 mins left',
                                style: TextStyle(fontSize: 16),
                              ),
                            ],
                          ),
                          8.rHorizontalSizedBox(),
                          Column(
                            children: [
                              Icon(
                                Icons.hourglass_bottom,
                                color: Colors.grey,
                              ),
                            ],
                          ),
                        ],
                      )
                    ],
                  )
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Order ID',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      GestureDetector(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => OrderDetailScreen(),
                            ),
                          );
                        },
                        child: Text(
                          '2231',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: 16,
                          ),
                        ),
                      ),
                    ],
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Address',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        '121 Yale St, City, Country',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ),
              18.rVerticalSizedBox(),
              Row(
                children: [
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement call driver functionality
                      },
                      icon: Icon(Icons.phone),
                      label: Text(
                        'Call Driver',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 2,
                      ),
                    ),
                  ),
                  12.rHorizontalSizedBox(),
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement track driver functionality
                      },
                      icon: Icon(Icons.my_location),
                      label: Text(
                        'Track Order',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                          side: BorderSide(
                            color: Colors.green.shade800,
                            width: 1,
                          ),
                        ),
                        elevation: 3,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ).paddingAll16(),
        ),
      ),
    );
  }
}
