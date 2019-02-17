# -*- coding: UTF-8 -*-


def file_collector(output_file_name):
    """文件收集器."""
    opened_output_file = open(output_file_name, 'w', encoding='utf-8')
    while True:
        try:
            line = yield
            opened_output_file.write(line)
        except GeneratorExit as e:
            opened_output_file.close()
            raise e


# #############################################################################
# use demo

def main():
    output_file_name = 'out.log'

    # 定义文件收集器
    collector = file_collector(output_file_name)
    next(collector)  # 让收集器生效

    # 向收集器发送数据
    for idx in range(0, 10):
        line = 'line %03d' % idx
        collector.send(line + '\n')

    # 关闭文件收集器
    collector.close()


if __name__ == '__main__':
    main()
