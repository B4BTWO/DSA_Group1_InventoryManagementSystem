G#1 ABINAL, GALANG, PACAY

1. Inventory Management System
Description: This system will manage products in a warehouse, allowing for efficient tracking of stock levels, orders, and suppliers.
Key Features:
Add, update, delete, and search for inventory items.
Track supplier information and stock levels.
Generate low-stock and restock reports.
Class Structure:
Product: Properties include id, name, quantity, supplier, and price.
Supplier: Properties include id, name, and contactInfo.
InventoryManager: Manages products and suppliers, with methods to add, update, and search for items.
Data Structures/Algorithms:
Use a linked list for storing inventory items for dynamic memory allocation.
Implement a binary search tree for organizing products by category or price for efficient searching.
Use a hash map for quick access to supplier details.
