-- 环境说明：当前数据库中存储的 key是用户唯一性标识，value代表优惠券id。要求判断用户是否使用过此优惠券
-- 键名用户唯一性标识,由执行命令的脚本传递
local key = KEYS[1]

-- 优惠券Id,由java的方法的其他参数传递
local couponId = ARGV[1]
-- 从redis读取用户是否使用
local isUse = redis.call('get',key)

-- 判断redis中的锁与其他参数是否一致
-- 注意 如果isUse在redis中没有，isUse也不是一个nil的存在，我看的其他博客说的是table
if isUse ~= couponId then
    redis.call('set','result','用户优惠券与目标优惠券不一致')
    redis.call('set',key,couponId)
    return 0
else
    redis.call('set','result','用户已经使用过此优惠券了')
    return 1
end

return -1