const content = document.querySelector(".cont_area_cos5");

const getLecture = (str) => {
  const idx1 = str.indexOf("(");
  const idx2 = str.lastIndexOf("차시");
  return Number(str.substring(idx1 + 1, idx2));
};

const getTime = (str) => {
  const idx1 = str.indexOf(" ");
  const idx2 = str.indexOf("시간");
  const idx3 = str.indexOf("분");

  if (idx2 == -1) {
    return Number(str.substring(0, idx3));
  } else {
    const hour = Number(str.substring(0, idx2));
    const min = Number(str.substring(idx1 + 1, idx3));
    return hour * 60 + min;
  }
};

JSON.stringify(
  [...content.children].map((el) => {
    const name = el.querySelector(".costit").textContent;
    const subt = el.querySelector(".cossubt").textContent;
    return {
      name,
      lecture: getLecture(subt),
      time: getTime(subt),
    };
  })
);
