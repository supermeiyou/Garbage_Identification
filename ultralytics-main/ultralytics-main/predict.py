from ultralytics import YOLO

model = YOLO("D:/graduate/ultralytics-main/ultralytics-main/runs/detect/train23/weights/best.pt")
results = model.predict(source="D:/graduate/images/message.jpg", save=True, save_conf=True, save_txt=True,
                        name='output')
