class EndPoints {
  static const appToken = 'oauth/token';
  static const login = 'auth/signin';
  static const signup = 'auth/signup';
  static const forgotPassword = 'auth/forget-password';
  static const resetPassword = 'auth/reset-password';

  static const getAverageRating = 'ratings/average';
  static const createOrder = 'orders/create';
  static const updatePaymentStatus = 'payments/updateStatus';
  static const createRating = 'ratings/createrating';
  static const updateTransactionStatus = 'transactions/updateStatus';
  static const paymentHistoryBuyer = 'payments/buyerPaymentHistory';
  static const paymentHistoryStore = 'payments/sellerPaymentHistory';
  static const createPayment = 'payments/createPaymentAndTransaction';
  static const updateOrderStatus = 'orders/updateStatus';
  static const getOrderDetails = 'orders/getDetails';
  static const getOrdersForUser = 'orders/getForUser';
  static const getOrdersForStore = 'orders/getForStore';
  static const sellerUpdateDetails = 'stores/updateStore';
  static const addCategory = 'categories'; // append userid
  static const addSubCategory = 'subcategories'; // append userid
  static const addMenuItem = 'menu-items'; // append userid
  static const getMenuItem = 'getMenuItemsByStore'; // append userid

  static const addAddress = 'users/addresses'; // append userid
  static const getNearByStores = 'users/latlong'; // append userid
  static const updateNotificationStatus =
      'users/toggle-notification'; // append userid
}
