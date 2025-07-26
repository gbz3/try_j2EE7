<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><html>
<head>
    <meta charset="utf-8">
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { background-color: #f9f9f9; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        label { display: block; margin-bottom: 10px; }
        input[type="checkbox"] { margin-right: 5px; }
        input[type="date"] {
            width: calc(100% - 22px); /* Adjust for padding/border */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; /* Include padding and border in the element's total width and height */
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 15px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h2>Hello World!</h2>
<form action="hello" method="post">
    <div class="form-group">
        <label for="startDate">開始日:</label>
        <input type="date" id="startDate" name="birth-start" value="2025-07-12">
    </div>

    <div class="form-group">
        <label for="endDate">終了日:</label>
        <input type="date" id="endDate" name="birth-end" value="2025-07-17">
    </div>
    <br>
    <input type="submit" value="送信">
</form>
</body>
</html>
