<template>
    <div>
      <Bar :data="dataCollection" :options="options" />
    </div>
  </template>
  
  <script setup>
  import { defineProps } from 'vue'
  import { Bar } from 'vue-chartjs';
  import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js';
  import ChartDataLabels from 'chartjs-plugin-datalabels';

  const props = defineProps({
  starPoints: {
    type: Object,
    required: true,
    default: null
  }})

  ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, ChartDataLabels);
  const rating5 = props.starPoints ? props.starPoints.find(item => item.rating === 5)?.count || 0 : 0;
  const rating4 = props.starPoints ? props.starPoints.find(item => item.rating === 4)?.count || 0 : 0;
  const rating3 = props.starPoints ? props.starPoints.find(item => item.rating === 3)?.count || 0 : 0;
  const rating2 = props.starPoints ? props.starPoints.find(item => item.rating === 2)?.count || 0 : 0;
  const rating1 = props.starPoints ? props.starPoints.find(item => item.rating === 1)?.count || 0 : 0;

  const options = {
    responsive: false,
    maintainAspectRatio: false,
    animation: {
        duration: 0
    },
    scales: {
      y: {
      display: false, 
      suggestedMax: Math.max(rating5, rating4, rating3, rating2, rating1) + 1, 
      grid: {
        color: 'transparent',
      },
    },
      x: {
      beginAtZero: true,
        scaleLineColor: 'red',
          grid: {
            color: 'transparent',
          },
       },
    },

    plugins:{ 
      
      legend: {
        display: false
      },
      tooltip: { // 기존 툴팁 사용 안 함
        enabled: false
      },
    }
  };
  
  const dataCollection = {
    labels: ['5점', '4점', '3점', '2점', '1점'],
    datasets: [
      {
        datalabels: {
        anchor: 'end', // 데이터 레이블을 막대의 끝에 위치
        align: 'end', // 데이터 레이블을 막대의 오른쪽에 정렬
        offset: 4, // 데이터 레이블을 막대의 위로 약간 올림
        color: '#36A2EB',
      },
        backgroundColor: '#f87979',
        pointBackgroundColor: 'white',
        borderWidth: 1,
        pointBorderColor: '#249EBF',
        data: [rating5, rating4, rating3, rating2, rating1]
      }
    ]
  };
  </script>
  