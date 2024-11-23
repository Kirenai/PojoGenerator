import { Link } from 'react-router-dom'
import { Box, Container, Heading, Text, VStack } from '@chakra-ui/react'
import { Button } from '@/components/ui/button'

const HomePage = () => {
  return (
    <VStack pt="16" pb="20">
      <Container>
        <VStack gap="10">
          <VStack alignSelf="flex-start" maxW="3xl" gap="5">
            <Heading alignSelf="start" size="5xl" as="h1">
              Pojo Generator <br /> herramienta para crear POJOs
            </Heading>
            <Text textStyle="2xl" color="gray.400" fontSize="xl">
              El principal objecto es facilitar el desarrollo al evitar la
              creación manual de clases Java para representar estructuras de
              datos complejas DTOs
            </Text>
          </VStack>
          <Box
            w="full"
            data-state="open"
            _open={{
              animationName: 'fade-in, scale-in',
              animationDuration: '300ms',
            }}
          >
            <Link to={'/pojo'}>
              <Button
                variant="surface"
                colorPalette="teal"
                minW="180px"
                size="xl"
              >
                Start Generator
              </Button>
            </Link>
          </Box>
        </VStack>
      </Container>
    </VStack>
  )
}

export default HomePage
